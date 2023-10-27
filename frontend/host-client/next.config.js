/*** @type {import('next').NextConfig}*/
module.exports = {
    output: "standalone",
    images: {unoptimized: true},
    reactStrictMode: true,
    pageExtensions: ['js', 'jsx', 'md', 'mdx', 'ts', 'tsx'],
    eslint: {
        dirs: ['pages', 'components', 'lib', 'layouts', 'scripts'],
    },
    webpack(config, {dev, isServer}) {
        config.resolve.fallback = {fs: false}
        config.module.rules.push({
            test: /\.svg$/i,
            issuer: /\.[jt]sx?$/,
            use: ['@svgr/webpack'],
        })
        return config
    },
}
