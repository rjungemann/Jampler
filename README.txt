Jampler
    by Roger Jungemann
    http://thefifthcircuit.com/

== DESCRIPTION:

Jampler is a Java Sampler. Over OSC, you pass it a url with a JSON object
containing a hash of names and urls of audio files. It loads those audio
files, then allows you to trigger those samples over OSC.

There are three working parts, the Java OSC Server, the Ruby File Server, and
the Ruby Client. The Ruby File Server isn't required, but exposes files which
can be opened by the server. The Java OSC Server receives "/play" events from
the Ruby Client, and plays audio in response.

== FEATURES/PROBLEMS:

* Improve Java Client.
* Improve Ruby Client. Add EventMachine. Implement music-making library on top.
* Implement panning.
* Allow user to send a "stop" event.

== SYNOPSIS:

Currently each component needs to be run separately, so you will need three
separate terminals to run everything.

Make sure the $java_home variable is set correctly in the Rakefile.

From the first terminal, open the file server by typing in

  rake java:build
  rake java:run

From the second terminal, run the OSC server by typing in

  rake server:run
  
Finally, from the third terminal, run the client by typing in

  rake client:run
  
You should hear a "bleep" sound.

You can edit "lib/main.rb" for more sophisticated behavior. You can also
create your own OSC clients. There are only two OSC methods so far:

* "/play"—three arguments: "name" (String), "velocity" (float), and "panning"
(float)
* "/load_samples"—one argument: "samples" (String)

The "samples" string above should be JSON in the form:

  {
    "sample-name": "sample-url",
    ...
  }
  
Review the existing Ruby client for more information for now. 

== REQUIREMENTS:

* Eclipse and Java 1.5 or greater with Ant
* Ruby 1.8
* A platform with fork support

== INSTALL:

== LICENSE:

(The MIT License)

Copyright (c) 2009 thefifthcircuit.

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
