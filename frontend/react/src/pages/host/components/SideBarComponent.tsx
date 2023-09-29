import {
  Card,
  Typography,
  List,
  ListItem,
  ListItemPrefix,
} from "@material-tailwind/react";

import { Link } from "react-router-dom";

export function SideBarComponenet() {
  return (
    <Card className="h-[calc(100vh-2rem)] w-full max-w-[20rem] p-4 shadow-xl shadow-blue-gray-900/5 bg-l-lilac">
      <div className="mb-2 p-4">
        <Typography variant="h5" color="blue-gray">
        Meny
        </Typography>
      </div>
      <List>
        <ListItem>
          <ListItemPrefix>
            icon
          </ListItemPrefix>
          <Link to="/host/host3/requests">Förfrågningar</Link>
        </ListItem>
        <ListItem>
          <ListItemPrefix>
            icon
          </ListItemPrefix>
          <Link to="/host/host3/settings">Inställningar </Link>
        </ListItem>

        {/* Finns inga vyer för ännu */}
{/*  
        <ListItem>
          <ListItemPrefix>
            <UserCircleIcon className="h-5 w-5" />
          </ListItemPrefix>
          Profile
        </ListItem>
        <ListItem>
          <ListItemPrefix>
            <Cog6ToothIcon className="h-5 w-5" />
          </ListItemPrefix>
          Settings
        </ListItem>
        <ListItem>
          <ListItemPrefix>
            <PowerIcon className="h-5 w-5" />
          </ListItemPrefix>
          Log Out
        </ListItem> */}
      </List>
    </Card>
  );
}
