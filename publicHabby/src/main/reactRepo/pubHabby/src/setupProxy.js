const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  console.log("proxy-middleware working...");
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http://localhost:8081",
      changeOrigin: true,
    })
  );
};
