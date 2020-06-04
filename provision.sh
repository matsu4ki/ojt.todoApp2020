# !/bin/sh

# upgrade packages
# dnf updateはdnf upgradeのエイリアス
# 効果に違いはない（dnf updateは将来廃止されるかもしれない）
# https://qiita.com/yunano/items/eedd3da40d67394881a2
#####################
# 時間がかかるのでdisabled
# sudo dnf upgrade -y
#####################

# locale setting
echo '==================\nlocale setting start\n=================='
sudo dnf install -y glibc-langpack-ja
localectl set-locale LANG=ja_JP.utf8
localectl status
echo '==================\nlocale setting end\n=================='

# 時刻の同期を行う
echo '==================\nsync time start\n=================='
date && chronyc -a makestep && date
echo '==================\nsync time end\n=================='

# install expect
sudo dnf install -y expect

# DB settings
echo '==================\nmysql setting start\n=================='
sudo dnf remove -y mariadb
sudo dnf install -y @mysql:8.0
sudo systemctl enable mysqld
sudo systemctl start mysqld
expect -c "
spawn mysql_secure_installation
expect \"Press y|Y for Yes, any other key for No: y\"
send  \"y\n\"
expect \"Please enter 0 = LOW, 1 = MEDIUM and 2 = STRONG:\"
send  \"0\n\"
expect \"New password:\"
send  \"rootuser\n\"
expect \"Re-enter new password:\"
send  \"rootuser\n\"
expect \"Do you wish to continue with the password provided?\"
send  \"y\n\"
expect \"Remove anonymous users? (Press y|Y for Yes, any other key for No) :\"
send  \"y\n\"
expect \"Disallow root login remotely? (Press y|Y for Yes, any other key for No)\"
send  \"n\n\"
expect \"Remove test database and access to it? (Press y|Y for Yes, any other key for No) :\"
send  \"y\n\"
expect \"Reload privilege tables now? (Press y|Y for Yes, any other key for No) :\"
send  \"y\n\"
expect "

# mysql user setting
# 外部からrootで繋げられるようにする
expect -c "
spawn mysql -u root -p -e \"CREATE USER 'root'@'%' IDENTIFIED BY 'rootuser'\"
expect \"Enter password:\"
send  \"rootuser\n\"
spawn mysql -u root -p -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION\"
expect \"Enter password:\"
send  \"rootuser\n\"
expect "
echo '==================\nmysql setting end\n=================='