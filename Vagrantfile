Vagrant.configure("2") do |config|
  config.vm.box = "centos/8"
  config.vm.box_url = "http://cloud.centos.org/centos/8/x86_64/images/CentOS-8-Vagrant-8.1.1911-20200113.3.x86_64.vagrant-virtualbox.box"
  config.vm.network "private_network", ip: "192.168.33.10"
  config.vm.network "forwarded_port", guest: 3306, host: 3306
  config.vm.hostname = "centos8"
  config.vm.provider "virtualbox" do |vb|
    vb.name = "centos8"
    vb.cpus = 2
    vb.gui = false
    vb.customize ["modifyvm", :id, "--memory", "4096"]
  end
  config.vm.provision :shell, :path => "./provision.sh"
end
