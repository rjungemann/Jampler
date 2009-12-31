require 'rubygems'
require 'ruby-osc/lib/ruby-osc'
require 'tap'

OSC::Client.new(57110).tap { |c|
  c.send OSC::Message.new('/load_samples', "http://localhost:4567/assets/samples.json")
  
  sleep 4
  
  c.send OSC::Message.new('/play', "one", 1.0, 0.0)
}