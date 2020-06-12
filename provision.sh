# !/bin/sh
# OverView
# This script is CentOS8 initializer used by Vagrant.

# Set Function
function SectionLine(){
   i=100
   printf "%${i}s\n" | tr ' ' '-'
   echo "$1"
   printf "%${i}s\n" | tr ' ' '-'
}


############################################################################################
# locale setting
############################################################################################
SectionLine "locale setting"
sudo dnf install -y glibc-langpack-ja langpacks-ja
localectl set-locale LANG=ja_JP.utf8
localectl status


############################################################################################
# upgrade packages
# dnf updateはdnf upgradeのエイリアス
# 効果に違いはない（dnf updateは将来廃止されるかもしれない）
# https://qiita.com/yunano/items/eedd3da40d67394881a2
############################################################################################
SectionLine "upgrade packages"
sudo dnf clean all
sudo rm -r /var/cache/dnf
sudo dnf upgrade -y


############################################################################################
# sync time
############################################################################################
SectionLine "sync time"
timedatectl set-timezone Asia/Tokyo
date && chronyc -a makestep && date


############################################################################################
# install expect
############################################################################################
SectionLine "install expect"
sudo dnf install -y expect


############################################################################################
# mysql settings
############################################################################################
SectionLine "mysql install"
sudo dnf remove -y mariadb
sudo dnf install -y @mysql:8.0
sudo systemctl enable mysqld
sudo systemctl start mysqld

SectionLine "mysql setting"
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
expect "

#
expect -c "
spawn mysql -u root -p -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION\"
expect \"Enter password:\"
send  \"rootuser\n\"
expect "

# create Database
expect -c "
spawn mysql -u root -p -e \"CREATE DATABASE if not exists todo\"
expect \"Enter password:\"
send  \"rootuser\n\"
expect "


############################################################################################
# Install Vim
############################################################################################
SectionLine "install vim"
sudo dnf -y install vim

############################################################################################
# Install tar
############################################################################################
SectionLine "install tar"
sudo dnf -y install tar

############################################################################################
# Setup nginx
############################################################################################
SectionLine "install nginx"
sudo dnf install -y nginx

SectionLine "Backup Conf"
sudo cp /etc/nginx/nginx.conf /etc/nginx/nginx.conf.org
sudo ls -ltar /etc/nginx/nginx.conf*

SectionLine "Set /etc/nginx/nginx.conf"
sudo cp -f /vagrant/conf/nginx.conf /etc/nginx/nginx.conf
sudo cat /etc/nginx/nginx.conf

SectionLine "Set /etc/nginx/conf.d/tomcat.conf"
sudo cp -f /vagrant/conf/tomcat.conf /etc/nginx/conf.d/tomcat.conf
sudo cat /etc/nginx/conf.d/tomcat.conf

SectionLine "Enable nginx"
sudo systemctl enable nginx.service

SectionLine "Start nginx"
sudo systemctl start nginx.service
sudo systemctl status nginx.service

############################################################################################
# Setup openjdk
############################################################################################
SectionLine "Create /etc/yum.repos.d/adoptopenjdk.repo"
sudo echo '[AdoptOpenJDK]
name=AdoptOpenJDK
baseurl=http://adoptopenjdk.jfrog.io/adoptopenjdk/rpm/centos/8/x86_64
enabled=1
gpgcheck=1
gpgkey=https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public' > /etc/yum.repos.d/adoptopenjdk.repo
sudo cat /etc/yum.repos.d/adoptopenjdk.repo

SectionLine "install adoptopenjdk"
sudo dnf -y install adoptopenjdk-11-hotspot.x86_64

SectionLine "java version"
java -version

############################################################################################
# Setup tomcat
############################################################################################
SectionLine "Useradd tomcat"
sudo useradd -s /sbin/nologin tomcat
cat /etc/passwd | grep tomcat

SectionLine "install tomcat9"
cd /tmp
sudo curl -O https://ftp.tsukuba.wide.ad.jp/software/apache/tomcat/tomcat-9/v9.0.36/bin/apache-tomcat-9.0.36.tar.gz
sudo tar -zxvf apache-tomcat-9.0.36.tar.gz -C /opt

sudo ln -s /opt/apache-tomcat-9.0.36 /opt/apache-tomcat
sudo chown -R tomcat. /opt/apache-tomcat-9.0.36
sudo ls -ltar /opt/apache-tomcat
sudo ls -ltar /opt/apache-tomcat-9.0.36

SectionLine "Set /etc/profile.d/tomcat.sh"
sudo echo "export CATALINA_HOME=/opt/apache-tomcat" > /etc/profile.d/tomcat.sh
sudo cat /etc/profile.d/tomcat.sh

SectionLine "Create /etc/tomcat"
sudo ln -s /opt/apache-tomcat/conf /etc/tomcat
sudo ls -ltar /etc/tomcat

SectionLine "Set Environment"
echo "export SPRING_PROFILES_ACTIVE=prod" > /opt/apache-tomcat/bin/setenv.sh
sudo chown tomcat. /opt/apache-tomcat/bin/setenv.sh

SectionLine "Create tomcat.service"
echo '[Unit]
Description=Apache Tomcat 9
After=syslog.target network.target

[Service]
User=tomcat
Group=tomcat
Type=oneshot
PIDFile=/opt/apache-tomcat/tomcat.pid
RemainAfterExit=yes

ExecStart=/opt/apache-tomcat/bin/startup.sh
ExecStop=/opt/apache-tomcat/bin/shutdown.sh
ExecReStart=/opt/apache-tomcat/bin/shutdown.sh;/opt/apache-tomcat/bin/startup.sh

[Install]
WantedBy=multi-user.target' > /etc/systemd/system/tomcat.service
sudo cat /etc/systemd/system/tomcat.service

SectionLine "setup war file"
sudo /vagrant/gradlew build
sudo mv /vagrant/build/libs/ojt.todoApp2020-0.0.1-SNAPSHOT.war /opt/apache-tomcat/webapps/ROOT.war

SectionLine "Enable tomcat.service"
sudo chmod 755 /etc/systemd/system/tomcat.service
sudo ls -ltar /etc/systemd/system/tomcat.service
sudo systemctl enable tomcat.service

SectionLine "Start tomcat.service"
sudo systemctl start tomcat.service
sudo systemctl status tomcat.service
