import InvariantError from '../../exceptions/InvariantError';
import UploadsSchema from './schema';

const { ImageHeadersSchema } = UploadsSchema;
const UploadsValidator = {
  validateImageHeaders: (headers) => {
    const validationResult = ImageHeadersSchema.validate(headers);
    if (validationResult.error) {
      throw InvariantError(validationResult.error.message);
    }
  },
};

export default UploadsValidator;
