import { Typography } from "@material-tailwind/react";

interface IMessageToClientComponent {
  text: string;
  color: string;
}

export const MessageToClientComponent = ({
  text,
  color,
}: IMessageToClientComponent) => {


  let style = "";

  switch (color) {
    case "red":
      style = `italic text-red text-2xl`;
      break;
    case "green":
      style = `italic text-green text-2xl`;
      break;
  }
  
  return (
    <Typography className={style}>{text}</Typography>
  );
};
