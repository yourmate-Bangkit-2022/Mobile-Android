import ClientError from './ClientError';

const AuthenticationError = (message) => ({
  ...ClientError(message),
  name: 'AuthenticationError',
  statusCode: 401,
});

export default AuthenticationError;
