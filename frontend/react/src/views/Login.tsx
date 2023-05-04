import "./Login.css";

const Login = () => {
  return (
    <>
      <div className="page text-white">
        <h1 className="text-2xl">Logga in</h1>
        <div className="input-container">
          <label className="mail-label" htmlFor="mail">E-post</label>
          <input type="email" name="mail" id="mail" />
        </div>

        <div className="input-container">
          <label htmlFor="password">Lösenord</label>
          <input type="password" name="password" id="password" />
        </div>

        <button className="bg-gray-400 my-4" type="button">Logga in</button>
      </div>
    </>
  );
};

export default Login;
