import {
  Card,
  Typography,
  List,
  ListItem,
  ListItemPrefix,
  ListItemSuffix,
  Chip,
} from "@material-tailwind/react";

import { Link } from "react-router-dom";

interface ISideBarComponent{
  name: string;
}

export function SideBarComponenet({name} : ISideBarComponent) {
  return (
    <Card className="h-[calc(100vh-2rem)] w-full max-w-[20rem] p-4 shadow-xl shadow-blue-gray-900/5 bg-l-lilac">
      <div className="mb-2 p-4">
        <Typography variant="h5" color="blue-gray">
        Välkommen {name}
        </Typography>
      </div>
      <List>
        <ListItem>
          <ListItemPrefix>
            icon
          </ListItemPrefix>
          <Link to="/guest/1/vacancies">Boka sängplats </Link>
        </ListItem>
        <ListItem>
          <ListItemPrefix>
            icon
          </ListItemPrefix>
          <Link to="/guest/1/reservations">Reservationer </Link>
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
