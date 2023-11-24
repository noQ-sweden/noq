"use client"
import React from 'react';
import Link from "next/link";
import Button2 from "@/libs/Button2";

const ConfirmationPage = () => {
  return (
      <main>
        <div className={"flex flex-col md:items-center gap-4 p-5"}>
          <p className={"font-bold"}>Din förfrågan har nu skickats till bostället!</p>
          <p>Plats för en informativ text om vad som sker härnest och eventuella instruktioner</p>
          <Link href={`/requests`}>
            <Button2 title={"Mina Bokningar"} onClick={() => {
            }} isLoading={false}/>
          </Link>
        </div>
      </main>
  );
};

export default ConfirmationPage;
