import ClientError from './ClientError';

const InvariantError = (message) => ({
  ...ClientError(message),
  name: 'InvariantError',
});

export default InvariantError;
