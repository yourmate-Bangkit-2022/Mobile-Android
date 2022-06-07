const CONFIG = {
  PG_OPTIONS: {
    database: process.env.PGDATABASE,
    host: process.env.HOST,
    password: process.env.PGPASSWORD,
    port: process.env.PGPORT,
    user: process.env.PGUSER,
  },
  DEBUG: process.env.DEBUG,
  RABBITMQ_SERVER: process.env.RABBITMQ_SERVER,
  MAIL_ADDRESS: process.env.MAIL_ADDRESS,
  MAIL_PASSWORD: process.env.MAIL_PASSWORD,
  REDIS_SERVER: process.env.REDIS_SERVER,
  ERROR: {
    RESPONSE: {

    },
  },
};

export default CONFIG;
