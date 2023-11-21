"use client"
import React, {useEffect, useState} from 'react';
import {BookingsPageDTO, FilterSearchReqBody} from "@/components/bookings/BookingsPageDTO";
import AvailableBookingItem from "@/components/bookings/components/AvailableBookingItem";
import {useRouter, useSearchParams} from "next/navigation";

interface BookingsProps {
  data: BookingsPageDTO
}

const Bookings = (props: BookingsProps) => {
  const searchParams = useSearchParams()
  const router = useRouter();

  const [bookingsPageDTO, setBookingsPageDTO] = useState<BookingsPageDTO>(props.data);
  const [defaultAreaSelected, setDefaultAreaSelected] = useState(searchParams.get('area') || "Välj bostadsområde");
  const [defaultSortSelected] = useState(searchParams.get('sort') || "featured");

  const [filterSearchContext, setFilterSearchContext] = useState<FilterSearchReqBody>({
    area: searchParams.get('area') || "ANY",
    sort: searchParams.get('sort') || "newest",
  });
  console.log(filterSearchContext)
  useEffect(() => {
    setBookingsPageDTO(props.data)
  }, [props.data]);

  const onChangeOptionArea = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const value = event.currentTarget.value;
    setFilterSearchContext(prevState => ({...prevState, area: value}));
    const areaValue = event.currentTarget.value
    const currentParams = new URLSearchParams(window.location.search)
    const sort = currentParams.get('sort') || "newest"
    const updatedUrl = `/bookings?area=${areaValue}&sort=${sort}`
    router.push(updatedUrl);
  };

  const onChangeOptionSort = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const value = event.currentTarget.value;
    setFilterSearchContext(prevState => ({...prevState, sort: value}));
    const sortValue = event.currentTarget.value
    const currentParams = new URLSearchParams(window.location.search)
    const area = currentParams.get('area') || "ANY"
    const updatedUrl = `/bookings?area=${area}&sort=${sortValue}`
    router.push(updatedUrl);
  };
  console.log(bookingsPageDTO)
  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12 gap-1"}>

            <section aria-label={"select-area"} className={"md:w-5/12 lg:w-4/12"}>
              <select id="countries"
                      className="bg-gray-50 border border-emerald-700 text-emerald-900 text-sm rounded focus:ring-emerald-700 focus:border-emerald-700
                      block w-full p-2.5 hover:border-emerald-700"
                      defaultValue={defaultAreaSelected}
                      onChange={event => onChangeOptionArea(event)}
              >
                <option value={"ANY"}>Välj bostadsområde</option>
                <option value="STOCKHOLM">Stockholm</option>
                <option value="UPPSALA">Uppsala</option>
              </select>
            </section>

            <section aria-label={"select-sort"} className={"md:w-5/12 lg:w-4/12"}>
              <select id="sort"
                      className="bg-gray-50 border border-emerald-700 text-emerald-900 text-sm rounded focus:ring-emerald-700 focus:border-emerald-700
                      block w-full p-2.5 hover:border-emerald-700"
                      defaultValue={defaultSortSelected}
                      onChange={event => onChangeOptionSort(event)}
              >
                <option value={"newest"}>Nyast först</option>
                <option value="oldest">Äldst först</option>
              </select>
            </section>

            <div className={"flex flex-col gap-1"}>
              {bookingsPageDTO.availableHosts && bookingsPageDTO.availableHosts.map(availableHost => {
                return (
                    <div key={availableHost.id}>
                      <AvailableBookingItem availableHost={availableHost}
                                    requestsViewModel={bookingsPageDTO}
                                    setRequestsViewModel={setBookingsPageDTO}
                      />
                    </div>
                )
              })}
            </div>
          </div>
        </main>
      </div>
  );
};

export default Bookings;
