const ClientError = (message) => ({
  ...Error,
  message,
  statusCode: 400,
  isCustomError: true,
});

export default ClientError;
