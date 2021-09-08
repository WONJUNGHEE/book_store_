module.exports = {
	reactStrictMode: true,
	devServer: {
		proxy: {
			'/api': {
				target: 'localhost:8000',
				changeOrigin: true,
			},
		},
	},
};
