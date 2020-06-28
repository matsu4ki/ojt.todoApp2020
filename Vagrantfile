Vagrant.configure("2") do |config|
  config.vm.box = "centos/8"
  config.vm.box_url = "http://cloud.centos.org/centos/8/x86_64/images/CentOS-8-Vagrant-8.1.1911-20200113.3.x86_64.vagrant-virtualbox.box"
  config.vm.network "private_network", ip: "192.168.33.10"
  config.vm.network "forwarded_port", guest: 3306, host: 3306
  config.vm.network "forwarded_port", guest: 8080, host: 8888
  config.vm.hostname = "centos8"
  config.vm.provider "virtualbox" do |vb|
    vb.name = "centos8"
    vb.cpus = 2
    vb.gui = false
    vb.memory = 2048
  end
  # vbguestを無効化する
  # https://sakamata.hateblo.jp/entry/2019/06/24/123601
  if Vagrant.has_plugin?("vagrant-vbguest")
    config.vbguest.auto_update = false
  end
  config.vm.provision :shell, :path => "./provision.sh"
end
