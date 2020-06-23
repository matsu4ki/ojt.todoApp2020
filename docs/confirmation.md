# 開発環境を構築する

以下のソフトをインストールしてください。
ダウンロードページが分からなかった場合、直リンクも掲載しているのでそちらからダウンロードしてください。
また、複数のインストーラー(x86とx64)がある場合は、x64の方をインストールしてください。

## Java11

Javaは様々な会社から提供されておりますが、
今回はAmazonが提供している、AmazonCorrettoを利用します。

- [Javaの概要](http://www.tohoho-web.com/java/about.htm)

- ダウンロード
  - [Amazon Correttoのダウンロードページ](https://aws.amazon.com/jp/corretto/)
  - [直リンク](https://corretto.aws/downloads/latest/amazon-corretto-11-x64-windows-jdk.msi)

## VirtualBox

仮想サーバーを立てるためのソフトウェアです。

- ダウンロード
  - [VirtualBoxのダウンロードページ](https://www.virtualbox.org/wiki/Downloads)
  - [直リンク](https://download.virtualbox.org/virtualbox/6.1.10/VirtualBox-6.1.10-138449-Win.exe)

## Vagrant

VirtualBoxをCLI上から操作するためのソフトウェア。

- ダウンロード
  - [Vagrantのダウンロードページ](https://www.vagrantup.com/downloads.html)
  - [直リンク](https://releases.hashicorp.com/vagrant/2.2.9/vagrant_2.2.9_x86_64.msi)

## A5:SQL Mk-2

DataBase(DB)をGUI上で操作できるソフトウェア。

- ダウンロード
  - [A5:SQL Mk-2のダウンロードページ](https://a5m2.mmatsubara.com/)
  - [直リンク](https://ftp.vector.co.jp/72/92/2526/a5m2_2.15.0_x64.zip)

## MySQL

MySQLはOracle社が主に提供している、OSSのDatabaseです。
詳細が気になる人は、[このサイト](https://www.publickey1.jp/blog/10/mysql_2.html#:~:text=MySQL%E3%81%AF%E3%81%99%E3%81%A7%E3%81%AB%E5%A4%A7%E3%81%8D%E3%81%8F2,MariaDB%E3%80%8D%E3%81%AE%E9%96%8B%E7%99%BA%E3%82%92%E9%96%8B%E5%A7%8B%E3%80%82)
が参考になると思うので、読んでみてください。

Databaseは仮想環境内に構築するため、本サンプルでのインストールは不要です。

## Git

バージョン管理を行うためのソフトウェア。
バージョン管理やGitについては、以下のリンクを参考にしてください。

[Gitを使ったバージョン管理｜サル先生のGit入門【プロジェクト管理ツールBacklog】](https://backlog.com/ja/git-tutorial/intro/01/)

- ダウンロード
  - [Gitのダウンロードページ](https://git-scm.com/)
  - [直リンク](https://git-scm.com/download/win)

## IntelliJ IDEA CE

JetBrains社が開発したIDEです。
IDEとは統合開発環境のことで、開発を行う上で必要なものまるっと1つのソフトウェアとして提供されております。
類似のIDEとして、Eclipseがありますが、本サンプルではこちらを利用します。

- ダウンロード
  - [IntelliJ IDEA CEのダウンロードページ](https://www.jetbrains.com/ja-jp/idea/download/#section=windows)
  - [直リンク](https://www.jetbrains.com/ja-jp/idea/download/download-thanks.html?platform=windows&code=IIC)

以下画像に表示されているボタンをクリックして、コミュニティ版をダウンロードしてください。
![intellij](img/confirmation/intellij1.png)

## Node.js

npmを利用するために、Node.jsをインストールします。
Node.jsをインストールすると、npmも自動的にインストールされます。
npmとは、フロントエンド周りのパッケージ管理ソフト(ソフトウェアを管理するソフト)のことです。

- ダウンロード
  - [Node.jsのダウンロードページ](https://nodejs.org/ja/download/)
  - [直リンク](https://nodejs.org/dist/v12.18.1/node-v12.18.1-x64.msi)

### yarn

npmを少し便利にしたパッケージ管理ソフトです。
npmを利用してインストールすることが可能です。

1. `windows + R`を押す
2. `powershell`と入力する
3. `npm install -g yarn`と入力する

これでインストールが完了します。

## Gradle

Javaのライブラリ管理ソフトです。
npmのJavaライブラリ版と考えてもらっても大丈夫です。

- ダウンロード
  - [Gradleダウンロードページ](https://gradle.org/releases/)
  - [ダウンロード直リンク](https://gradle.org/next-steps/?version=6.5&format=all)
