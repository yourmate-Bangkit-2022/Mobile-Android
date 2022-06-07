import InvariantError from '../../exceptions/InvariantError';

const AuthenticationsService = (pool) => ({
  async addRefreshToken(token) {
    const query = {
      text: 'INSERT INTO authentications VALUES($1)',
      values: [token],
    };

    await pool.query(query);
  },
  async verifyRefreshToken(token) {
    const query = {
      text: 'SELECT token FROM authentications WHERE token = $1',
      values: [token],
    };

    const result = await pool.query(query);
    if (!result.rowCount) {
      throw InvariantError('Refresh token tidak valid');
    }
  },
  async deleteRefreshToken(token) {
    await this.verifyRefreshToken(token);

    const query = {
      text: 'DELETE FROM authentications WHERE token = $1',
      values: [token],
    };

    await pool.query(query);
  },
});

export default AuthenticationsService;
