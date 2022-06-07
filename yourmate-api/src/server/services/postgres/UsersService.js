import { nanoid } from 'nanoid';
import bcrypt from 'bcrypt';
import InvariantError from '../../exceptions/InvariantError';
import NotFoundError from '../../exceptions/NotFoundError';
import AuthenticationError from '../../exceptions/AuthenticationError';

const UsersService = (pool) => ({
  async addUser({
    username, password, fullname, email, phone, birthdate,
  }) {
    await this.verifyNewUsername(username);
    const id = `user-${nanoid(16)}`;
    const hashedPassword = await bcrypt.hash(password, 10);
    const query = {
      text: 'INSERT INTO users VALUES($1, $2, $3, $4, $5, $6, $7) RETURNING id',
      values: [id, username, hashedPassword, fullname, email, phone, birthdate],
    };
    const result = await pool.query(query);
    if (!result.rowCount) {
      throw InvariantError('User gagal ditambahkan');
    }
    return result.rows[0].id;
  },
  async verifyNewUsername(username) {
    const query = {
      text: 'SELECT username FROM users WHERE username = $1',
      values: [username],
    };
    const result = await pool.query(query);
    if (result.rowCount) {
      throw InvariantError('Gagal menambahkan user. Username sudah digunakan.');
    }
  },
  async getUserById(userId) {
    const query = {
      text: 'SELECT id, username, fullname FROM users WHERE id = $1',
      values: [userId],
    };
    const result = await pool.query(query);
    if (!result.rowCount) {
      throw NotFoundError('User tidak ditemukan');
    }
    return result.rows[0];
  },
  async verifyUserCredential(username, password) {
    const query = {
      text: 'SELECT id, password FROM users WHERE username = $1',
      values: [username],
    };
    const result = await pool.query(query);
    if (!result.rowCount) {
      throw AuthenticationError('Kredensial yang Anda berikan salah');
    }
    const { id, password: hashedPassword } = result.rows[0];
    const match = await bcrypt.compare(password, hashedPassword);
    if (!match) {
      throw AuthenticationError('Kredensial yang Anda berikan salah');
    }
    return id;
  },
});

export default UsersService;
