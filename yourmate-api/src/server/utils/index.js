/* eslint-disable camelcase */
// created_at, updated_at //

import CONFIG from '../../config/config';

export const errorHandler = {
  checkAndResponse: ({ response, h }) => {
    const { data: error } = response;

    if (error.isCustomError) {
      const newResponse = h.response({
        status: 'fail',
        message: error.message,
      });
      newResponse.code(error.statusCode);
      return newResponse;
    }

    const responsePayload = {
      status: 'error',
      message: 'Maaf, terjadi kegagalan pada server kami.',
    };
    if (CONFIG.DEBUG) responsePayload.error = error.message;
    const newResponse = h.response(responsePayload);
    newResponse.code(500);
    return newResponse;
  },
};

const Utils = {
  errorHandler,
};

export default Utils;
