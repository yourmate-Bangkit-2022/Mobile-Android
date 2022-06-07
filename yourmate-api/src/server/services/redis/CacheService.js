const CacheService = (client) => ({
  set(key, value, expirationInSecond = 3600) {
    return new Promise((resolve, reject) => {
      client.set(key, value, 'EX', expirationInSecond, (error, ok) => {
        if (error) return reject(error);
        return resolve(ok);
      });
    });
  },
  get(key) {
    return new Promise((resolve, reject) => {
      client.get(key, (error, reply) => {
        if (error) {
          return reject(error);
        }
        if (reply === null) {
          return reject(new Error('Cache tidak ditemukan'));
        }
        return resolve(reply.toString());
      });
    });
  },
  delete(key) {
    return new Promise((resolve, reject) => {
      client.del(key, (error, count) => {
        if (error) {
          return reject(error);
        }
        return resolve(count);
      });
    });
  },
});

export default CacheService;
