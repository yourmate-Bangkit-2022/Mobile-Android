import ClientError from './ClientError';

const NotFoundError = (message) => ({
  ...ClientError(message),
  name: 'NotFoundError',
  statusCode: 404,
});

export default NotFoundError;
