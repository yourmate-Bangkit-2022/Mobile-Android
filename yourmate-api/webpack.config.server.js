const path = require('path');
const nodeExternals = require('webpack-node-externals');

const CURRENT_WORKING_DIR = process.cwd();
const Dotenv = require('dotenv-webpack');
const { IgnorePlugin } = require('webpack');

module.exports = {
  mode: 'development',
  name: 'server',
  entry: [path.join(CURRENT_WORKING_DIR, './src/server')],
  target: 'node',
  output: {
    path: path.join(CURRENT_WORKING_DIR, '/dist/server'),
    filename: 'server.generated.js',
    publicPath: '/dist/server',
    libraryTarget: 'commonjs2',
  },
  externals: [nodeExternals()],
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: ['babel-loader'],
      },
      { // Kanggo kapayun
        test: /\.(ttf|eot|svg|gif|jpg|png)(\?[\s\S]+)?$/,
        use: 'file-loader',
      },
    ],
  },
  plugins: [
    new Dotenv({ path: './.env' }),
    new IgnorePlugin({ resourceRegExp: /^pg-native$/ }),
  ],
};
