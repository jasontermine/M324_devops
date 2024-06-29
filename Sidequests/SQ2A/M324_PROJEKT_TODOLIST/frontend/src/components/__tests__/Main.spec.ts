import { describe, it, expect, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import Main from '@/components/Main.vue'
import { createPinia, setActivePinia } from 'pinia'

describe('Main component tests', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })
  
  /**
   * Tests if the Main component renders / contains the correct text 
   */
  it('renders properly', () => {
    const wrapper = mount(Main)
    expect(wrapper.find("#title").text()).toStrictEqual("ToDo Liste")
    expect(wrapper.find("#submitBtn").text()).toStrictEqual('Absenden')
  });

})
