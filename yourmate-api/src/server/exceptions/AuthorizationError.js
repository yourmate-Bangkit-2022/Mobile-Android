import ClientError from './ClientError';

const AuthorizationError = (message) => ({
  ...ClientError(message),
  name: 'AuthorizationError',
  statusCode: 403,
});

export default AuthorizationError;
