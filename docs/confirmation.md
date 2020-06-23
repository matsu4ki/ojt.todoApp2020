# 開発環境を構築する

以下のソフトをインストールしてください。

## Java11

Amazonが提供している、AmazonCorrettoを利用します。

[Amazon Correttoのダウンロードページ](https://aws.amazon.com/jp/corretto/)

## VirtualBox

仮想サーバーを立てるためのソフトウェアです。

[VirtualBoxのダウンロードページ](https://www.virtualbox.org/wiki/Downloads)

## Vagrant

VirtualBoxをCLI上から操作するためのソフトウェア。

[Vagrantのダウンロードページ](https://www.vagrantup.com/downloads.html)

## A5:SQL Mk-2

DataBase(DB)をGUI上で操作できるソフトウェア。

[A5:SQL Mk-2のダウンロードページ](https://a5m2.mmatsubara.com/)

## MySQL

MySQLはOracle社が主に提供している、OSS(無償で公開されているソフトウェアのこと。OpenSourceSoftwareの略)のDatabaseです。
Databaseは仮想環境内に構築するため、本サンプルでのインストールは不要です。

MySQLの開発陣営について、は以下が参考になると思います。

[MySQL開発者はどこへ行ってしまったのだろう？ 大丈夫、多くはオラクルにとどまっている － Publickey](https://www.publickey1.jp/blog/10/mysql_2.html#:~:text=MySQL%E3%81%AF%E3%81%99%E3%81%A7%E3%81%AB%E5%A4%A7%E3%81%8D%E3%81%8F2,MariaDB%E3%80%8D%E3%81%AE%E9%96%8B%E7%99%BA%E3%82%92%E9%96%8B%E5%A7%8B%E3%80%82)

## Git

バージョン管理を行うためのソフトウェア。
バージョン管理やGitについては、以下のリンクを参考にしてください。

[Gitを使ったバージョン管理｜サル先生のGit入門【プロジェクト管理ツールBacklog】](https://backlog.com/ja/git-tutorial/intro/01/)

ダウンロードは以下から
[Gitのダウンロードページ](https://git-scm.com/)

## IntelliJ IDEA CE

JetBrains社が開発したIDEです。
IDEとは統合開発環境のことで、開発を行う上で必要なものまるっと1つのソフトウェアとして提供されております。
類似のIDEとして、Eclipseがありますが、本サンプルではこちらを利用します。

[IntelliJ IDEA CEのダウンロードページ](https://www.jetbrains.com/ja-jp/idea/download/#section=windows)

以下画像に表示されているボタンをクリックして、コミュニティ版をダウンロードしてください。
![intellij](img/confirmation/intellij1.png)

## Node.js

npmを利用するために、Node.jsをインストールします。
Node.jsをインストールすると、npmも自動的にインストールされます。
npmとは、フロントエンド周りのパッケージ管理ソフト(ソフトウェアを管理するソフト)のことです。

[Node.jsのダウンロードページ](https://nodejs.org/ja/download/)

### yarn

npmを少し便利にしたパッケージ管理ソフトです。
npmを利用してインストールすることが可能です。

1. `windows + R`を押す
2. `powershell`と入力する
3. `npm install -g yarn`と入力する

これでインストールが完了します。

## gradle

Javaのライブラリ管理ソフトです。
npmのJavaライブラリ版と考えてもらっても大丈夫です。

[Gradle | Thank you for downloading Gradle!](https://gradle.org/next-steps/?version=6.5&format=all)
