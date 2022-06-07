const AuthenticationsHandler = ({
  authenticationsService, usersService, validator, tokenManager,
}) => ({
  async postAuthenticationHandler(request, h) {
    validator.validatePostAuthenticationPayload(request.payload);
    const { username, password } = request.payload;
    const id = await usersService.verifyUserCredential(username, password);
    const accessToken = tokenManager.generateAccessToken({ id });
    const refreshToken = tokenManager.generateRefreshToken({ id });
    await authenticationsService.addRefreshToken(refreshToken);
    const response = h.response({
      status: 'success',
      message: 'Authentication berhasil ditambahkan',
      data: {
        accessToken,
        refreshToken,
      },
    });
    response.code(201);
    return response;
  },
  async putAuthenticationHandler(request) {
    validator.validatePutAuthenticationPayload(request.payload);
    const { refreshToken } = request.payload;
    await authenticationsService.verifyRefreshToken(refreshToken);
    const { id } = tokenManager.verifyRefreshToken(refreshToken);
    const accessToken = tokenManager.generateAccessToken({ id });
    return {
      status: 'success',
      message: 'Access Token berhasil diperbarui',
      data: {
        accessToken,
      },
    };
  },
  async deleteAuthenticationHandler(request) {
    validator.validateDeleteAuthenticationPayload(request.payload);
    const { refreshToken } = request.payload;
    await authenticationsService.deleteRefreshToken(refreshToken);
    return {
      status: 'success',
      message: 'Refresh token berhasil dihapus',
    };
  },
});

export default AuthenticationsHandler;
