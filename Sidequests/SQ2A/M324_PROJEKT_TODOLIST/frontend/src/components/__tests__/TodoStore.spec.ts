import { useTodoStore } from '@/stores/todoStore'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach, describe, expect, it } from 'vitest'

beforeEach(() => {
    setActivePinia(createPinia());
})

describe('TodoStore status', () => {

  it('TodoState status is 0 by default', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toBe(0)
  })

  it('TodoState status default value is smaller than test value', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toBeLessThan(1)
  })

})

describe('TodoStore data', () => {

    it('TodoState data is empty by default', () => {
        const todoList = useTodoStore()

        expect(todoList.data).toHaveLength(0)
    })

  })