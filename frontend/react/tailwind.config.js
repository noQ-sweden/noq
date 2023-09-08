/** @type {import('tailwindcss').Config} */
const withMT = require("@material-tailwind/react/utils/withMT");

module.exports = withMT({
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    colors: {
      "gray": "#f1f5f9",
      "lightgray": "#d4d4d4",
      "lilac": "#AEC4FD",
      "l-lilac": "#eff3fe",
      "d-lilac": "#6C76CF",
      "red": "#dc2626",
      "green": "#65a30d",
      "pink" : "#EFB9E8",
      "purple" : "#A78ED5",
    },
    container: {
      center: true,
    },
    extend: {},
  },
  plugins: [],
});
