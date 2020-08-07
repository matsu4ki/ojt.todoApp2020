Vagrant.configure("2") do |config|
  config.vm.box = "centos/8"
  # [Vagrant CentOS8 v1905.1 で失敗する人 - Qiita](https://qiita.com/hatman621221/items/06cc73ea3be90f51b503)
  config.vbguest.installer_options = { allow_kernel_upgrade: true }
  config.vm.network "private_network", ip: "192.168.33.10"
  config.vm.network "forwarded_port", guest: 3306, host: 3306
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.hostname = "centos8"
  config.vm.provider "virtualbox" do |vb|
    vb.name = "centos8"
    vb.cpus = 2
    vb.gui = false
    vb.memory = 2048
  end
  # rsync
  # [040-virtualbox共有フォルダーを使用してLinuxの問題を解決する-ナゲット](https://juejin.im/post/6844903917608763406)
  config.vm.synced_folder ".", "/vagrant", type: "rsync",
  rsync__verbose: true,
  rsync__auto: true,
  rsync__exclude: ['.git*', 'node_modules*','*.log','*.box','Vagrantfile']

  # vbguestを無効化する
  # https://sakamata.hateblo.jp/entry/2019/06/24/123601
  # if Vagrant.has_plugin?("vagrant-vbguest")
  #   config.vbguest.auto_update = false
  # end
  config.vm.provision :shell, :path => "./provision.sh"
end
