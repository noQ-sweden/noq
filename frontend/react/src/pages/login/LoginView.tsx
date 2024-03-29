import "./Login.css";


const Login = () => {
  /* const { clientId } = useContext(ClientContext);
  const navigate = useNavigate();

  const hostId = "host4";

  const getHost = async () => {
    try {
      navigate(`/host/${hostId}`);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchClient = async () => {
    try {
      const response = await getClient(clientId);
      const clientData = response?.data;
  
      console.log(clientData?.reservation);
      clientData?.reservation
        ? navigate(`/reservation/${clientData?.id}`)
        : navigate(`/vacancies/${clientData?.id}`);
      console.log(clientData);
    } catch (error) {
      console.error(error);
    }
  };
  const loginClient = async () => {
    await fetchClient();
  };

  const loginHost = async () => {
    await getHost();
  };

  return (
    <>
      <div className="min-h-screen flex items-center justify-center">
        <div className="bg-white rounded-lg shadow-lg p-8 w-80 max-w-sm min-width: 100%">
          <h2 className="text-2xl font-bold mb-6">Välkommen</h2>
          <div className="flex flex-col space-y-4">
            <div className="flex items-center rounded-md border border-gray-300">
              <span className="rounded-l-md px-3 py-2">
                <FaPortrait />
              </span>
              <input
                type="email"
                placeholder="E-post"
                className="flex-grow px-1 py-2 rounded-r-md focus:outline-none"
              />
            </div>
            <div className="flex items-center rounded-md border border-gray-300">
              <span className=" rounded-l-md px-3 py-2">
                <FaLock />
              </span>
              <input
                type="password"
                placeholder="Lösenord"
                className="px-1 py-2 rounded-r-md focus:outline-none"
              />
            </div>
          </div>
          <button
            className="bg-blue-500 text-white rounded-full px-1 mt-6"
            onClick={loginClient}
          >
            Brukare
          </button>
          <button
            className="bg-blue-500 text-white rounded-full px-1 mt-6"
            onClick={loginHost}
          >
            Härberge
          </button>
        </div>
      </div>
    </>
  ); */
};
export default Login;
