const UploadsHandler = ({ service, validator }) => ({
  async postUploadImageHandler(request, h) {
    const { data } = request.payload;
    validator.validateImageHeaders(data.hapi.headers);
    const filename = await service.writeFile({ dir: 'images', file: data, meta: data.hapi });
    const response = h.response({
      status: 'success',
      message: 'Gambar berhasil diunggah',
      data: {
        pictureUrl: `http://${process.env.HOST}:${process.env.PORT}/upload/images/${filename}`,
      },
    });
    response.code(201);
    return response;
  },
});

export default UploadsHandler;
