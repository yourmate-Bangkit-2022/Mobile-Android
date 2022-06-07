import routes from './routes';
import AuthenticationsHandler from './handler';

const authentications = {
  name: 'authentications',
  version: '1.0.0',
  register: async (server, {
    authenticationsService,
    usersService,
    tokenManager,
    validator,
  }) => {
    const authenticationsHandler = AuthenticationsHandler({
      authenticationsService,
      usersService,
      validator,
      tokenManager,
    });
    server.route(routes(authenticationsHandler));
  },
};

export default authentications;
