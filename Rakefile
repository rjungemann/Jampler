$java_home = "/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home/"
$server_port = 4567

namespace :java do
  task :clean do
    sh "export JAVA_HOME=\"#{$java_home}\" && cd Jampler && ant clean"
  end
  
  task :build do
    sh "export JAVA_HOME=\"#{$java_home}\" && cd Jampler && ant"
  end
  
  task :run do
    sh "export JAVA_HOME=\"#{$java_home}\" && cd Jampler && ant JavaSampler"
  end
end

namespace :server do
  task :run do
    sh "rackup config.ru -p#{$server_port}"
  end
end

namespace :client do
  task :run do
    sh "cd lib && ruby main.rb"
  end
end