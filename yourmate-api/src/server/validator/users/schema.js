import Joi from 'joi';

const UserPayloadSchema = Joi.object({
  username: Joi.string().required(),
  password: Joi.string().required(),
  fullname: Joi.string().required(),
  email: Joi.string().email().required(),
  phone: Joi.string().required(),
  birthdate: Joi.date().required(),
});

export default UserPayloadSchema;
