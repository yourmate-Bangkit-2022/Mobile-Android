const UsersHandler = ({ service, validator }) => ({
  async postUserHandler(request, h) {
    validator.validateUserPayload(request.payload);
    const {
      username, password, fullname, email, phone, birthdate,
    } = request.payload;
    const userId = await service.addUser({
      username, password, fullname, email, phone, birthdate,
    });
    const response = h.response({
      status: 'success',
      message: 'User berhasil ditambahkan',
      data: {
        userId,
      },
    });
    response.code(201);
    return response;
  },
  async getUserByIdHandler(request) {
    const { id } = request.params;
    const user = await service.getUserById(id);
    return {
      status: 'success',
      data: {
        user,
      },
    };
  },
});

export default UsersHandler;
