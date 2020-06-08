const path = require('path');

module.exports = {
  // 起点となるファイル
  entry: {
    index: path.join(__dirname, 'src/main/resources/src/js/index.js'),
    login: path.join(__dirname, 'src/main/resources/src/js/login.js'),
    create: path.join(__dirname, 'src/main/resources/src/js/create.js'),
  },
  // webpack watch したときに差分ビルドができる
  cache: true,
  // development は、 source map file を作成、再ビルド時間の短縮などの設定となる
  // production は、コードの圧縮やモジュールの最適化が行われる設定となる
  mode: 'development', // "production" | "development" | "none"
  // ソースマップのタイプ
  devtool: 'source-map',
  // 出力先設定 __dirname は node でのカレントディレクトリのパスが格納される変数
  output: {
    path: path.join(__dirname, 'src/main/resources/public/js'),
    filename: '[name].js',
  },
  // ファイルタイプ毎の処理を記述する
  module: {
    rules: [
      {
        // コンパイルの事前に eslint によるチェックを噛ます
        test: /\.js?$/,
        // 事前処理であることを示す
        enforce: 'pre',
        // JavaScript をコードチェックする
        loader: 'eslint-loader',
      },
      {
        test: /\.css/,
        use: [
          'style-loader',
          'css-loader',
          {
            loader: 'css-loader',
            options: {
              url: false,
            },
          },
        ],
      },
      {
        test: /\.s[ac]ss$/i,
        use: [
          'style-loader', // inject CSS to page
          'css-loader', // translates CSS into CommonJS modules
          'sass-loader',
        ],
      },
    ],
  },
  // 処理対象のファイルを記載する
  resolve: {
    extensions: [
      '.js', // node_modulesのライブラリ読み込みに必要
    ],
  },
};
