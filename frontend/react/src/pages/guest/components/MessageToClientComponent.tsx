import { Typography } from "@material-tailwind/react";

interface IMessageToClientComponent {
  text: string;
  color: string;
}

export const MessageToClientComponent = ({
  text,
  color,
}: IMessageToClientComponent) => {
  return (
    <Typography className={`italic text-${color} text-2xl`}>{text}</Typography>
  );
};
