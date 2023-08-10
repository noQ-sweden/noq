
import * as React from "react";
import HandleBedsComponent from "../../components/HandleBedsComponent";


export default function BedsView() {

    return (
            <main className="py-10 px-4 col-span-3">
                <div className="flex items-center justify-center">
                    <div className="p-4">
                        <HandleBedsComponent></HandleBedsComponent>
                    </div>
                </div>
            </main>
    
    );
}