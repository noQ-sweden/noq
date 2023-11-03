'use server'

import {revalidateTag} from 'next/cache'

export async function revalidateCache(tag: string) {
  revalidateTag(tag)
}
