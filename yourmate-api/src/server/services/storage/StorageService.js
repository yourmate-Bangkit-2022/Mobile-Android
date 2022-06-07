import fs from 'fs';
import path from 'path';

const StorageService = async (folder) => {
  if (!fs.existsSync(folder)) {
    await fs.mkdirSync(folder, { recursive: true });
  }
  return ({
    async writeFile({ dir, file, meta }) {
      if (!fs.existsSync(path.join(folder, dir))) {
        await fs.mkdirSync(path.join(folder, dir), { recursive: true });
      }
      const filename = +new Date() + meta.filename;
      const pathResolve = path.join(folder, dir, filename);
      const fileStream = fs.createWriteStream(pathResolve);
      return new Promise((resolve, reject) => {
        fileStream.on('error', (error) => reject(error));
        file.pipe(fileStream);
        file.on('end', () => resolve(filename));
      });
    },
  });
};

export default StorageService;
