export default class Toast {
  message: string;
  description: string;
  element: HTMLElement;

  constructor(message: string, description: string) {
    this.message = message;
    this.description = description;
    this.element = document.createElement('div');
    this.element.className = 'toast';
    this.element.innerHTML = `<strong>${this.message}</strong><hr><p>${this.description}</p>`;
    document.body.appendChild(this.element);
  }

  show() {
    requestAnimationFrame(() => {
      this.element.classList.add('show');
    });
  }

  timedShow(timeMs: number) {
    this.show();
    setTimeout(() => {
      this.hide();
    }, timeMs);
  }

  hide() {
    this.element.classList.remove('show');
    this.element.addEventListener('transitionend', () => {
      if (this.element.parentElement) {
        this.element.parentElement.removeChild(this.element);
      }
    });
  }
}
