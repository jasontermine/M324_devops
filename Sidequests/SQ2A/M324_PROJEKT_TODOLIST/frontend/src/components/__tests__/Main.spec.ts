import { describe, it, expect, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import Main from '../Main.vue'
import { createPinia, setActivePinia } from 'pinia'

beforeEach(() => {
  setActivePinia(createPinia());
})

describe('HelloWorld', () => {
  it('renders properly', () => {
    const wrapper = mount(Main)
    expect(wrapper.text()).toContain('ToDo Liste')
    expect(wrapper.find("button").text()).toContain('Absenden')
  })
})
