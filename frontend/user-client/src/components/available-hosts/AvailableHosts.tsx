"use client"
import React, {useEffect, useState} from 'react';
import {AvailableHostsDTO, FilterSearchReqBody} from "@/components/available-hosts/AvailableHostsDTO";
import AvailableBookingItem from "@/components/available-hosts/components/AvailableBookingItem";
import {useRouter, useSearchParams} from "next/navigation";
import {fetchPage} from "@/components/available-hosts/AvailableHostsAPI";
import LoadingSpinner from "@/libs/LoadingSpinner";

interface BookingsProps {
}

const BookingsPage = (props: BookingsProps) => {
  const searchParams = useSearchParams()
  const router = useRouter();

  const [availableHostsDTO, setAvailableHostsDTO] = useState<AvailableHostsDTO>();

  const [defaultAreaSelected, setDefaultAreaSelected] = useState(searchParams.get('area') || "Välj bostadsområde");
  const [defaultSortSelected] = useState(searchParams.get('sort') || "featured");

  const [filterSearchContext, setFilterSearchContext] = useState<FilterSearchReqBody>({
    area: searchParams.get('area') || "ANY",
    sort: searchParams.get('sort') || "newest",
  });

  useEffect(() => {
    fetchPage("").then(setAvailableHostsDTO)
  }, []);

  const onChangeOptionArea = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const value = event.currentTarget.value;
    setFilterSearchContext(prevState => ({...prevState, area: value}));
    const areaValue = event.currentTarget.value
    const currentParams = new URLSearchParams(window.location.search)
    const sort = currentParams.get('sort') || "newest"
    const updatedUrl = `/available-hosts?area=${areaValue}&sort=${sort}`
    router.push(updatedUrl);
  };

  const onChangeOptionSort = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const value = event.currentTarget.value;
    setFilterSearchContext(prevState => ({...prevState, sort: value}));
    const sortValue = event.currentTarget.value
    const currentParams = new URLSearchParams(window.location.search)
    const area = currentParams.get('area') || "ANY"
    const updatedUrl = `/available-hosts?area=${area}&sort=${sortValue}`
    router.push(updatedUrl);
  };

  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12 gap-1"}>
            <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4 mb-4">
              <div className="flex">
                <div className="flex-shrink-0">
                  <svg className="h-5 w-5 text-yellow-400" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd"/>
                  </svg>
                </div>
                <div className="ml-3">
                  <p className="text-sm text-yellow-700">
                    The shelter booking feature is temporarily disabled for the demo and soft launch.
                  </p>
                </div>
              </div>
            </div>

            <section aria-label={"select-area"} className={"md:w-5/12 lg:w-4/12"}>
              <select id="countries"
                       className="bg-gray-50 border border-primary text-emerald-900 text-sm rounded focus:ring-emerald-700 focus:border-emerald-700
                       block w-full p-2.5 hover:border-primary"
                      defaultValue={defaultAreaSelected}
                      onChange={event => onChangeOptionArea(event)}
              >
                <option value={"ANY"}>Välj bostadsområde</option>
                <option value="STOCKHOLM">Stockholm</option>
                <option value="UPPSALA">Uppsala</option>
              </select>
            </section>

       {/*      <section aria-label={"select-sort"} className={"md:w-5/12 lg:w-4/12"}>
              <select id="sort"
                      className="bg-gray-50 border border-emerald-700 text-emerald-900 text-sm rounded focus:ring-emerald-700 focus:border-emerald-700
                      block w-full p-2.5 hover:border-emerald-700"
                      defaultValue={defaultSortSelected}
                      onChange={event => onChangeOptionSort(event)}
              >
                <option value={"newest"}>Nyast först</option>
                <option value="oldest">Äldst först</option>
              </select> 
            </section>*/}
            {availableHostsDTO ? <>
              <div className={"flex flex-col gap-1"}>
                {availableHostsDTO.availableHosts && availableHostsDTO.availableHosts.map(availableHost => {
                  return (
                      <div key={availableHost.hostId}>
                        <AvailableBookingItem availableHost={availableHost}
                                              requestsViewModel={availableHostsDTO}
                                              setRequestsViewModel={setAvailableHostsDTO}
                        />
                      </div>
                  )
                })}
              </div>
            </> : <>
              <LoadingSpinner/>
            </>}

          </div>
        </main>
      </div>
  );
};

export default BookingsPage;
