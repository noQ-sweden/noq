import {
  Card,
  Typography,
  List,
  ListItem,
  ListItemPrefix,
  ListItemSuffix,
  Chip,
} from "@material-tailwind/react";
import {
  UserCircleIcon,
  Cog6ToothIcon,
  InboxIcon,
  PowerIcon,
  CalendarDaysIcon,
  FolderArrowDownIcon,
  
} from "@heroicons/react/24/solid";
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
            <CalendarDaysIcon className="h-7 w-7" />
          </ListItemPrefix>
          <Link to="/host/host3/beds">Förfrågningar</Link>
        </ListItem>
        <ListItem>
          <ListItemPrefix>
            <FolderArrowDownIcon className="h-7 w-7" />
          </ListItemPrefix>
          <Link to="/host/host3/requests">Sängplatser </Link>
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
