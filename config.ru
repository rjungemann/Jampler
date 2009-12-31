run Rack::URLMap.new(
  "/assets" => Rack::File.new("public")
)