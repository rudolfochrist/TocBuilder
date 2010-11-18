require "ant"
require "rake/clean"

CLEAN.include('build', 'toc.xml')

namespace :ivy do
  ivy_install_version = "2.0.0"
  ivy_jar_dir = "./ivy"
  ivy_jar_file = "#{ivy_jar_dir}/ivy.jar"
  
  desc "Download Apache Ivy"
  task :download do
    mkdir_p ivy_jar_dir
    ant.get :src => "http://repo1.maven.org/maven2/org/apache/ivy/ivy/#{ivy_install_version}/ivy-#{ivy_install_version}.jar",
      :dest => ivy_jar_file, :usetimestamp => true
  end
  
  desc "Download and install Apache Ivy"
  task :install => :download do
    ant.path :id => "ivy.lib.path" do
      fileset :dir => ivy_jar_dir, :includes => "*.jar"
    end
    
    ant.taskdef :resource => "org/apache/ivy/ant/antlib.xml", :classpathref => "ivy.lib.path"
  end
  
  desc "Install dependencies"
  task :go => "ivy:install" do
    ant.retrieve :organisation => "com.thoughtworks.xstream", 
      :module => "xstream", 
      :revision => "1.3.1", 
      :pattern => "lib/[artifact].[ext]", 
      :inline => "true"
  end
end

desc  "Compile sources"
task :compile do
  mkdir_p "bin"
  
  ant.path :id => "project.class.path" do
    pathelement :location => "bin"
    pathelement :location => "lib/xstream.jar"
  end
  
  ant.javac :srcdir => "src", 
    :destdir => "bin", 
    :classpathref => "project.class.path",
    :includeAntRuntime => false
end

desc "Execute"
task :run => :compile do
  puts "Executing project"
  ant.java :classname => "tree.Main", 
    :classpathref => "project.class.path",
    :fork => true
end

desc "Build executable jar"
task :build => :compile do
  puts "Build executable jar in /build"
  mkdir_p "build"
  ant.jar :destfile => "build/TocBuilder.jar", :basedir => "bin" do
    
  end
end 
  
task :default => :run