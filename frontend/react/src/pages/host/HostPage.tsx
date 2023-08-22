import React, {useEffect, useState} from 'react'
import { SideBarComponenet } from './components/SideBarComponent'
import { Outlet } from 'react-router-dom'
import { HostPageProvider } from '../../context/HostPageContext'

export default function HostPage() {

    return (
    <>
    <HostPageProvider>
     <div className="mt-12 flex">
        <div className="flex-none">
          <aside>
            <SideBarComponenet></SideBarComponenet>
          </aside>
        </div>
        <div className="grow">
          <Outlet />
        </div>
      </div>
    </HostPageProvider>
    </>
  )
}
