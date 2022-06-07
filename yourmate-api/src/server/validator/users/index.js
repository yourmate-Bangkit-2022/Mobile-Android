import UserPayloadSchema from './schema';
import InvariantError from '../../exceptions/InvariantError';

const UsersValidator = {
  validateUserPayload: (payload) => {
    const validationResult = UserPayloadSchema.validate(payload);
    if (validationResult.error) {
      throw InvariantError(validationResult.error.message);
    }
  },
};

export default UsersValidator;
