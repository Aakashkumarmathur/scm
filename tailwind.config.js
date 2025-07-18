/** @type {import('tailwindcss').Config} */
export default {
    content: ["./src/main/resources/**/*.{html,js}"],
    theme: {
      extend: {
        backgroundColor: ['hover'],
      },
    },
    plugins: [],
    darkMode: "selector",
  };