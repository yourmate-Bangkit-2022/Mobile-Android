import UsersHandler from './handler';
import routes from './routes';

const users = {
  name: 'users',
  version: '1.0.0',
  register: async (server, { service, validator }) => {
    const usersHandler = UsersHandler({ service, validator });
    server.route(routes(usersHandler));
  },
};

export default users;
