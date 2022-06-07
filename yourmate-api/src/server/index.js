import Hapi from '@hapi/hapi';
import { Pool } from 'pg';
import Jwt from '@hapi/jwt';
import path from 'path';
import Inert from '@hapi/inert';
import { errorHandler } from './utils';
import CONFIG from '../config/config';
import UsersService from './services/postgres/UsersService';
import users from './api/users';
import UsersValidator from './validator/users';
import AuthenticationsService from './services/postgres/AuthenticationsService';
import authentications from './api/authentications';
import TokenManager from './tokenize/TokenManager';
import AuthenticationsValidator from './validator/authentications';
import StorageService from './services/storage/StorageService';
import uploads from './api/uploads';
import UploadsValidator from './validator/uploads';

const init = async () => {
  const pool = new Pool(CONFIG.PG_OPTIONS);
  const usersService = UsersService(pool);
  const authenticationsService = AuthenticationsService(pool);
  const storageService = await StorageService(path.resolve(process.cwd(), 'files/upload'));
  const server = Hapi.server({
    port: process.env.PORT,
    host: process.env.HOST,
    routes: {
      cors: {
        origin: ['*'],
      },
    },
  });

  await server.register([
    {
      plugin: Jwt,
    },
    {
      plugin: Inert,
    },
  ]);

  const preResponse = (request, h) => {
    const { response } = request;

    if (response.isBoom && response.isServer) {
      return errorHandler.checkAndResponse({ response, h });
    }

    return response;
  };

  server.ext('onPreResponse', preResponse);

  server.auth.strategy('yourmatesapp_jwt', 'jwt', {
    keys: process.env.ACCESS_TOKEN_KEY,
    verify: {
      aud: false,
      iss: false,
      sub: false,
      maxAgeSec: process.env.ACCESS_TOKEN_AGE,
    },
    validate: (artifacts) => ({
      isValid: true,
      credentials: {
        id: artifacts.decoded.payload.id,
      },
    }),
  });

  await server.register([
    {
      plugin: users,
      options: {
        service: usersService,
        validator: UsersValidator,
      },
    },
    {
      plugin: authentications,
      options: {
        authenticationsService,
        usersService,
        tokenManager: TokenManager,
        validator: AuthenticationsValidator,
      },
    },
    {
      plugin: uploads,
      options: {
        service: storageService,
        validator: UploadsValidator,
      },
    },
  ]);

  await server.start();
  return server;
};

init()
  .then((server) => {
    console.log(`Server berjalan pada ${server.info.uri}`);
  }).catch((err) => {
    console.log(err);
  });
