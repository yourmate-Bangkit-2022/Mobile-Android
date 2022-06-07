import UploadsHandler from './handler';
import routes from './routes';

const uploads = {
  name: 'uploads',
  version: '1.0.0',
  register: async (server, { service, validator }) => {
    const uploadsHandler = UploadsHandler({ service, validator });
    server.route(routes(uploadsHandler));
  },
};

export default uploads;
